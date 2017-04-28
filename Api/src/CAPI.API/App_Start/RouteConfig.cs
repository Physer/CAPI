using System.Web.Mvc;
using System.Web.Routing;

namespace CAPI.API.App_Start
{
    public static class RouteConfig
    {
        public static void RegisterMvcRoutes(RouteCollection routes)
        {
            routes.MapRoute(
                name: "Capi",
                url: "capi",
                defaults: new {controller = "Capi", action = "Index"}
            );
        }
    }
}