package com.driver.Service;

import com.driver.model.Booking;
import com.driver.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class BookingService {
    @Autowired
            HotelService hotelService;

    HashMap<String,Booking> bookingdb;
    public BookingService(){
        this.bookingdb=new HashMap<String , Booking>();
    }
    public String bookroom(Booking booking){
        // ID GENERATED AUTOMATICALLY , input object do not contain any id attribute.
        String id=UUID.randomUUID().toString();
        booking.setBookingId(id);
        bookingdb.put(id,booking);
        return "Booking Successfully Added";
    }

    public int getTotalBookingPriceByUser(Booking booking){
        int roomsbooked=booking.getNoOfRooms();
        Hotel hotel=null;
        if(hotelService.hoteldb.containsKey(booking.getHotelName())) hotel=hotelService.hoteldb.get(booking.getHotelName());
        if(hotel==null) return -1;
        if(hotel.getAvailableRooms()==0) return -1;
        int roomprice=hotel.getPricePerNight();
        int totalprice=roomsbooked*roomprice;
        booking.setAmountToBePaid(totalprice);
        return totalprice;
    }
}
