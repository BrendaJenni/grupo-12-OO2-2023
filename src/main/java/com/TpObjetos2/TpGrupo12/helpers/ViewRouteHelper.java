package com.TpObjetos2.TpGrupo12.helpers;

public class ViewRouteHelper {
	/**** Views ****/
	//Views
    //HomeController
    public final static String INDEX = "home/index";
    public final static String HELLO = "home/hello";

    //DispositivoController
    public final static String DISPOSITIVO_INDEX = "dispositivo/dispositivos";
    public final static String DISPOSITIVO_ROOT = "/dispositivo/";
    
    //ALABRADO
    public final static String ALUMBRADO_INDEX = "dispositivo/alumbrado";
    public final static String ALUMBRADO_ROOT = "dispositivo/alumbrado/";
    
    //EventoController
    public final static String EVENTO_INDEX = "evento/evento";
    public final static String EVENTO_ROOT = "/evento/";
    
    //MedicionController
    public final static String MEDICION_INDEX = "medicion/medicion";
    public final static String MEDICION_ROOT = "/medicion/";
    
  //USER
  	public final static String USER_LOGIN = "user/login";
  	public final static String USER_LOGOUT = "user/logout";

    //Redirect
    public final static String  ROUTE_INDEX = "/home";
}