using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Runtime.Serialization;
using System.Threading.Tasks;
using System.Web;
using Radzen;
using viewer.Constants;
using viewer.Model;

namespace viewer.service
{
    public class RequestComService : CommunicationService
    {
        public RequestComService(ConfigurationService conf, NotificationService notificationService, HttpClient httpClient) : base(notificationService, httpClient, conf)
        {
        }
        public async Task<IEnumerable<CRequestType>> GetRequestTypes()
        {
            var builder = new UriBuilder(_config.hostName + EndpointConstants.RequestTypeUrl);
            var query = HttpUtility.ParseQueryString(builder.Query);
            builder.Query = query.ToString();
            var url = builder.Uri;
            var result =  await ExecuteRequestMultiple<CRequestType>(url, HttpMethod.Get);
            if (result == null || !result.Any())
                NotifyServerFailed();
            return result;
        }
        
        public async Task<IEnumerable<String>> CreateRequest(Request newRequest)
        {
            var uri = new Uri(_config.hostName + EndpointConstants.NewRequestUrl);
            return await ExecuteNoResponse(uri, HttpMethod.Post, newRequest);
        }
        
    }
}