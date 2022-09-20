namespace viewer.Model
{
    public class Request
    {
        public string policyNumber { get; set; }
        public string name { get; set; }
        public string surname { get; set; }
        public string message { get; set; }
        public CRequestType type { get; set; }
    }
}