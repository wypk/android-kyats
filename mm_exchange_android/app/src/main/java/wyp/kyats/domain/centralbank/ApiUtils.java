package wyp.kyats.domain.centralbank;


import static wyp.kyats.foundation.ApiConstants.BASE_URL;

public class ApiUtils {

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
