using System.Web.Http;
using System.Web.Routing;
using CAPI.API.App_Start;

namespace CAPI.API
{
    public class WebApiApplication : System.Web.HttpApplication
    {
        protected void Application_Start()
        {
            GlobalConfiguration.Configure(WebApiConfig.Register);
            ContainerConfig.Register();
            RouteConfig.RegisterMvcRoutes(RouteTable.Routes);
        }
    }
}
