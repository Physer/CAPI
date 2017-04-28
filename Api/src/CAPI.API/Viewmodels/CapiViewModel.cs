using System.Collections.Generic;

namespace CAPI.API.Viewmodels
{
    public class CapiViewModel
    {
        public IEnumerable<string> CapiObjectImplementationNames { get; set; }
        public IEnumerable<string> CapiProviderImplementationNames { get; set; }
    }
}