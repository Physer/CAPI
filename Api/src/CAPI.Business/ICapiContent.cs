namespace CAPI.Business
{
    public interface ICapiContent<T>
    {
        T Identifier { get; }

        string Title { get; }

        string Description { get; }

        string ImageUrl { get; }
    }
}
