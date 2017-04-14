using System.Collections.Generic;
using CAPI.Business;

namespace CAPI.Services
{
    public interface IContentProvider<T>
    {
        IEnumerable<ICapiContent<T>> GetData();
        ICapiContent<T> GetData(T indentifier);
    }
}
