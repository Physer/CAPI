using System;
using CAPI.Business;
using System.Collections.Generic;
using System.Linq;

namespace CAPI.Services
{
    public class ContentProvider : IContentProvider<Guid>
    {
        private readonly ICapiContent<Guid>[] _capiContents =
            {
                new CapiContent<Guid>
                {
                    Description = "Visual Studio",
                    Identifier = new Guid("D68D166E-DC19-4026-AA22-71C8AC24CED8"),
                    ImageUrl = "https://pbs.twimg.com/profile_images/839220246690975744/zlVaaEoG.jpg",
                    Title = "Visual Studio"
                },

                new CapiContent<Guid>
                {
                    Description = "Android Studio",
                    Identifier = new Guid("243D2F77-74B0-4F8D-B2CC-C9ECD8771967"),
                    ImageUrl = "https://tctechcrunch2011.files.wordpress.com/2017/02/android-studio-logo.png",
                    Title = "Android Studio"
                },

                new CapiContent<Guid>
                {
                    Description = "Xamarin Studio",
                    Identifier = new Guid("401BCD6F-7AA7-427E-B005-9F5946C9E70D"),
                    ImageUrl = "https://s3.amazonaws.com/blog.xamarin.com/wp-content/uploads/2016/06/02150513/Xamarin-Studio.png",
                    Title = "Xamarin Studio"
                },
            };
        public IEnumerable<ICapiContent<Guid>> GetData() => _capiContents;

        public ICapiContent<Guid> GetData(Guid indentifier) => _capiContents.FirstOrDefault(content => content.Identifier.Equals(indentifier));
    }
}
