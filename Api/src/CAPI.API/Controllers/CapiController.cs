using System;
using System.Linq;
using System.Web.Mvc;
using CAPI.API.Viewmodels;
using CAPI.Business;
using CAPI.Services;

namespace CAPI.API.Controllers
{
    public class CapiController : Controller
    {
        public ActionResult Index()
        {
            var capiContentInterface = typeof(ICapiContent<Guid>);
            var capiProviderInterface = typeof(IContentProvider<Guid>);

            var capiContents = AppDomain.CurrentDomain.GetAssemblies()
                .SelectMany(s => s.GetTypes())
                .Where(p => capiContentInterface.IsAssignableFrom(p));

            var capiProviders = AppDomain.CurrentDomain.GetAssemblies()
                .SelectMany(s => s.GetTypes())
                .Where(p => capiProviderInterface.IsAssignableFrom(p));

            var viewModel = new CapiViewModel
            {
                CapiObjectImplementationNames = capiContents.Select(content => content.ToString()),
                CapiProviderImplementationNames = capiProviders.Select(content => content.ToString())
            };
            return View(viewModel);
        }
    }
}