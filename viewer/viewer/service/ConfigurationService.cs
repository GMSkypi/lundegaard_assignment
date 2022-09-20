namespace viewer.service
{
    public class ConfigurationService
    {
        public string hostName { get; }
        
        public ConfigurationService(string hostName)
        {
            this.hostName = hostName;
        }
    }
}