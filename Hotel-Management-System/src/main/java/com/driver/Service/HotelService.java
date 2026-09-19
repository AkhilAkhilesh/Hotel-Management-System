package com.driver.Service;

import com.driver.model.Facility;
import com.driver.model.Hotel;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import java.lang.String;

import java.util.*;

@Service
public class HotelService {
    private final RequestToViewNameTranslator requestToViewNameTranslator;

    HashMap<String, Hotel> hoteldb;
    public HotelService(RequestToViewNameTranslator requestToViewNameTranslator){
        this.hoteldb=new HashMap<String, Hotel>();
        this.requestToViewNameTranslator = requestToViewNameTranslator;
    }
    public String addhotelintodb(Hotel hotel){
        if(hotel!=null){
            if(hoteldb.containsKey(hotel.getHotelName())){
                return "Hotel Already Exists in DB";
            }
            hoteldb.put(hotel.getHotelName(),hotel);
        }
        return "Hotel Successfully Added";
    }

    public String hotelwithmostfecilities(){
        List<Hotel> li=new ArrayList<>();
        int maxlen=0;
        for(Hotel hotel:hoteldb.values()){
            li.add(hotel);
        }
        Collections.sort(li,(a,b)->{
            if(a.getFacilities().size()!=b.getFacilities().size()) return b.getFacilities().size()-a.getFacilities().size();
            return a.getHotelName().compareToIgnoreCase(b.getHotelName());
        });
        if(!li.isEmpty()) return li.get(0).getHotelName();
        return "";
    }

    public Hotel updatehotelfecilities(List<Facility> newfacilities, String hotelname){
        Hotel hotel=hoteldb.getOrDefault(hotelname,null);
        if(hotel!=null){
            List<Facility> oldfacilities=hotel.getFacilities();
            for(Facility facility:newfacilities){
                if(!oldfacilities.contains(facility)) oldfacilities.add(facility);
            }
            hotel.setFacilities(oldfacilities);
        }
        return hotel;
    }

    public Object getHotelByName(String name){
        Hotel hotel= hoteldb.getOrDefault(name,null);
        Map<String, Object> response = new HashMap<>();

        if (hotel != null) {
            response.put("Exists", hotel);
        } else {
            response.put("Hotel Doesn't Exist in DB", null);
        }

        return response;
    }
}
