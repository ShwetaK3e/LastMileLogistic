package com.shwetak3e.zentello.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shwetak3e.zentello.R;
import com.shwetak3e.zentello.adapter.BookDeliveryAdapter;
import com.shwetak3e.zentello.franchisee_activity.SignatureActivity;
import com.shwetak3e.zentello.models.Frnachisee;
import com.shwetak3e.zentello.models.Parcel;
import com.shwetak3e.zentello.user_activity.BookParcelDeliveryActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {

    //Splash Screen Timer
    private static int SPLASH_TIME_OUT = 3000;
    public static List<Frnachisee> franchisees=new ArrayList<>();
    public static Map<String,Parcel> parcels=new HashMap<>();
    public static Map<String, String> pinFrnachiseeMap=new HashMap<>();
    public static Map<String, String> pinRouteMap=new HashMap<>();
    public static Map<String ,Frnachisee.People> deliveryBoyRouteMap=new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        prepareJubileeFranchisee();
        prepareBanjaraFranchisee();
        prepareGachiBowliFranchisee();
        preparePinMap();
        preparePinRouteMap();
        prepareDeliveryManRouteMap();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //This method will be executed once the timer is over
                //Start your app main activity
                Intent i;
                i= new Intent(SplashActivity.this, RegisterActivity.class);
                SplashActivity.this.startActivity(i);

                // Close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    void prepareFranchiseeList(String name, String[] pincodes, List<Frnachisee.Route> routes, List<Frnachisee.People> deliveryPersons){
        Frnachisee frnachisee=new Frnachisee();
        frnachisee.setName(name);
        frnachisee.setPincodes(pincodes);
        frnachisee.setRoute(routes);
        frnachisee.setDeliveryPerson(deliveryPersons);
        franchisees.add(frnachisee);
    }

   void prepareJubileeFranchisee(){
       String name="DTDC, JUBILEE";
       String[] pincodes=new String[]{"500001","500002","500003","500081","500082","500083"};
       List<Frnachisee.Route> routes=new ArrayList<>();
       Frnachisee.Route route=new Frnachisee.Route();
       route.setRoute_name(name+"routeA");
       route.setPincodes_in_route(new String[]{pincodes[0],pincodes[1],pincodes[2]});
       routes.add(route);
       route.setRoute_name(name+"routeB");
       route.setPincodes_in_route(new String[]{pincodes[3],pincodes[4],pincodes[5]});
       routes.add(route);
       List<Frnachisee.People> deliveryPerson=new ArrayList<>();
       Frnachisee.People person=new Frnachisee.People();
       person.setId(name+"1");
       person.setName("Person1");
       person.setPhone("8093870668");
       deliveryPerson.add(person);
       person.setId(name+"2");
       person.setName("Person2");
       person.setPhone("8093870668");
       deliveryPerson.add(person);
       prepareFranchiseeList(name,pincodes,routes,deliveryPerson);
   }

    void prepareBanjaraFranchisee(){
        String name="DTDC, BANJARA";
        String[] pincodes=new String[]{"500004","500005","500006","500084","500085","500086"};
        List<Frnachisee.Route> routes=new ArrayList<>();
        Frnachisee.Route route=new Frnachisee.Route();
        route.setRoute_name(name+"routeA");
        route.setPincodes_in_route(new String[]{pincodes[0],pincodes[1],pincodes[2]});
        routes.add(route);
        route.setRoute_name(name+"routeB");
        route.setPincodes_in_route(new String[]{pincodes[3],pincodes[4],pincodes[5]});
        routes.add(route);
        List<Frnachisee.People> deliveryPerson=new ArrayList<>();
        Frnachisee.People person=new Frnachisee.People();
        person.setId(name+"1");
        person.setName("Person3");
        person.setPhone("8093870668");
        deliveryPerson.add(person);
        person.setId(name+"2");
        person.setName("Person4");
        person.setPhone("8093870668");
        deliveryPerson.add(person);
        prepareFranchiseeList(name,pincodes,routes,deliveryPerson);
    }

    void prepareGachiBowliFranchisee(){
        String name="DTDC, GACHIBOWLI";
        String[] pincodes=new String[]{"500007","500008","500009","500087","500088","500089"};
        List<Frnachisee.Route> routes=new ArrayList<>();
        Frnachisee.Route route=new Frnachisee.Route();
        route.setRoute_name(name+"routeA");
        route.setPincodes_in_route(new String[]{pincodes[0],pincodes[1],pincodes[2]});
        routes.add(route);
        route.setRoute_name(name+"routeB");
        route.setPincodes_in_route(new String[]{pincodes[3],pincodes[4],pincodes[5]});
        routes.add(route);
        List<Frnachisee.People> deliveryPerson=new ArrayList<>();
        Frnachisee.People person=new Frnachisee.People();
        person.setId(name+"1");
        person.setName("Person5");
        person.setPhone("8093870668");
        deliveryPerson.add(person);
        person.setId(name+"2");
        person.setName("Person6");
        person.setPhone("8093870668");
        deliveryPerson.add(person);
        prepareFranchiseeList(name,pincodes,routes,deliveryPerson);
    }

    void preparePinMap(){
        for(Frnachisee frnachisee: franchisees){
            String[] pincodes=frnachisee.getPincodes();
            for(String pincode:pincodes){
                pinFrnachiseeMap.put(pincode,frnachisee.getName());
            }
        }
    }

    void preparePinRouteMap(){
       for(Frnachisee frnachisee:franchisees){
           List<Frnachisee.Route> routes=frnachisee.getRoute();
           for(Frnachisee.Route route:routes){
               String[] pincodes=route.getPincodes_in_route();
               for(String pincode:pincodes){
                  pinRouteMap.put(pincode,route.getRoute_name());
               }
           }

       }
    }
    void prepareDeliveryManRouteMap(){
        for(Frnachisee frnachisee:franchisees){
            List<Frnachisee.Route> routes =frnachisee.getRoute();
            List<Frnachisee.People> deliveryPersons=frnachisee.getDeliveryPerson();
            for(int i=0; i<routes.size() && i<deliveryPersons.size();i++){
                deliveryBoyRouteMap.put(routes.get(i).getRoute_name(),deliveryPersons.get(i));
            }
        }
    }




}
