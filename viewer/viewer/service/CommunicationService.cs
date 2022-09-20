using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Text.Json;
using System.Text.Json.Serialization;
using System.Threading.Tasks;
using Radzen;
using viewer.service;

namespace viewer.service
{
    public class CommunicationService : IComunicationService
    {
        private readonly HttpClient _httpClient;
        
        private NotificationService _notificationService;

        protected ConfigurationService _config;
        
        protected CommunicationService(NotificationService notificationService, HttpClient httpClient, ConfigurationService config)
        {
            _notificationService = notificationService;
            _httpClient = httpClient;
            _config = config;
        }
        
        protected async Task<IEnumerable<String>> ExecuteNoResponse(Uri uri, HttpMethod method, Object optionalBody = null)
        {
            var httpRequestMessage = new HttpRequestMessage(method, uri);
            if (optionalBody != null)
            {
                var body = JsonSerializer.Serialize(optionalBody, new JsonSerializerOptions {Converters ={ new JsonStringEnumConverter()}});
                httpRequestMessage.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                httpRequestMessage.Content = new StringContent(body, Encoding.UTF8, "application/json");
            }

            try
            {
                var response = await _httpClient.SendAsync(httpRequestMessage);
                if (response.IsSuccessStatusCode)
                    return new List<String>();
                using var responseStream = await response.Content.ReadAsStreamAsync();
                Console.WriteLine("read stream");
                IEnumerable<String> result = await JsonSerializer.DeserializeAsync
                    <IEnumerable<String>>(responseStream,  new JsonSerializerOptions {Converters ={ new JsonStringEnumConverter()},});
                return result;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return new List<String>(){"Something went wrong"};
            }
            
        }
        protected async Task<IEnumerable<T>> ExecuteRequestMultiple<T>(Uri uri,HttpMethod method, Object optionalBody = null )
        {
            var httpRequestMessage = new HttpRequestMessage(method, uri);
            if (optionalBody != null)
            {
                var body = JsonSerializer.Serialize(optionalBody, new JsonSerializerOptions {Converters ={ new JsonStringEnumConverter()}});
                httpRequestMessage.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                httpRequestMessage.Content = new StringContent(body, Encoding.UTF8, "application/json");
            }
            try
            {
                var response = await _httpClient.SendAsync(httpRequestMessage);
                if (response.IsSuccessStatusCode)
                {
                    using var responseStream = await response.Content.ReadAsStreamAsync();
                    Console.WriteLine("read stream");
                    IEnumerable<T> result = await JsonSerializer.DeserializeAsync
                        <IEnumerable<T>>(responseStream,  new JsonSerializerOptions {Converters ={ new JsonStringEnumConverter()},});
                    return result;
                }
                return new List<T>();
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
            }
            
            NotifyServerFailed();
            return new List<T>();
        }
        
        protected void NotifyServerFailed()
        {
            
            NotificationMessage message = new NotificationMessage()
            {
                Severity = NotificationSeverity.Error, Summary = "Server failed to load data",
                Duration = 10000,
            };
            _notificationService.Notify(message);
        }
    }
}