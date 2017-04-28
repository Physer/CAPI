using System;
using System.Web.Http;
using CAPI.Business;
using CAPI.Services;
using DryIoc;
using DryIoc.WebApi;

namespace CAPI.API
{
    public static class ContainerConfig
    {
        public static void Register()
        {
            var container = new Container().WithWebApi(GlobalConfiguration.Configuration);
            container.Register<IContentProvider<Guid>, ContentProvider>();
            container.Register<ICapiContent<Guid>, CapiContent<Guid>>();
        }
    }
}