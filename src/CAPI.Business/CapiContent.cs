namespace CAPI.Business
{
    public class CapiContent<T> : ICapiContent<T>
    {
        public T Identifier { get; set; }
        public string Title { get; set; }
        public string Description { get; set; }
        public string ImageUrl { get; set; }
    }
}
