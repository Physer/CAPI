using System;
using System.Web.Http;
using CAPI.Services;

namespace CAPI.API.Controllers
{
    public class ContentController : ApiController
    {
        private readonly IContentProvider<Guid> _contentProvider;

        public ContentController(IContentProvider<Guid> contentProvider)
        {
            _contentProvider = contentProvider;
        }

        [HttpGet]
        public IHttpActionResult Get()
        {
            try
            {
                return Json(_contentProvider.GetData());
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
                return Json(_contentProvider.GetData(identifier));
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }
    }
}