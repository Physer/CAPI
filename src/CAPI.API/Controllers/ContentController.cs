using System;
using System.Web.Http;
using CAPI.Services;

namespace CAPI.API.Controllers
{
    public class ContentController : ApiController
    {
        [HttpGet]
        public IHttpActionResult Get()
        {
            try
            {
                var contentService = new ContentProvider();
                return Json(contentService.GetData());
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpGet]
        public IHttpActionResult Get(Guid identifier)
        {
            try
            {
                var contentService = new ContentProvider();
                return Json(contentService.GetData(identifier));
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }
    }
}