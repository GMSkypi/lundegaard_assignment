using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.AspNetCore.Components;
using Radzen;
using viewer.Model;
using viewer.service;

namespace viewer.Pages
{
    public partial class MainRequest : ComponentBase, IDisposable
    {
        [Inject] public RequestComService RequestComService { get; set; }
        
        [Inject] public NotificationService NotificationService { get; set; }


        private IEnumerable<CRequestType> _types;
        private Request newRequest { get; set; }
        
        public void Dispose()
        {
        }

        protected override async void OnInitialized()
        {
            newRequest = new Request();
            _types = await RequestComService.GetRequestTypes();
            StateHasChanged();
        }

        private async void Summit()
        {
            IEnumerable<string> valid = await RequestComService.CreateRequest(newRequest);
            if (valid.Any())
                foreach (string s in valid)
                {
                    NotificationMessage message = new NotificationMessage()
                    {
                        Severity = NotificationSeverity.Error, Summary = s,
                        Duration = 10000,
                    };
                    NotificationService.Notify(message);
                }
            else
            {
                NotificationMessage message = new NotificationMessage()
                {
                    Severity = NotificationSeverity.Success,
                    Summary = "The request has been sent successfully",
                    Style = "",
                    Duration = 10000,
                };
                NotificationService.Notify(message);
                newRequest = new Request();
                StateHasChanged();
            }
        }

    }
}