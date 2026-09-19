package com.driver.controllers;

import com.driver.Service.BookingService;
import com.driver.Service.HotelService;
import com.driver.Service.UserService;
import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/hotel")
public class HotelManagementController {
    private final BookingService bookingService;
    private final UserService userService;
    private final HotelService hotelService;

    public HotelManagementController(BookingService bookingService, UserService userService, HotelService hotelService){
        this.bookingService=bookingService;
        this.userService=userService;
        this.hotelService=hotelService;
    }
    @PostMapping("/add-hotel")
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel){
        if(hotel==null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        String result=hotelService.addhotelintodb(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel);
    }

    @PostMapping("/add-user")
    public ResponseEntity<?> addUser(@RequestBody User user){
        String result= userService.addUserintodb(user);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @GetMapping("/get-hotel-with-most-facilities")
    public ResponseEntity<?> getHotelWithMostFacilities(){
        String result= hotelService.hotelwithmostfecilities()+" is the hotel with most Facilities";
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/book-a-room")
    public ResponseEntity<?> bookARoom(@RequestBody Booking booking){
        String result=bookingService.bookroom(booking);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
        //return 0;
    }

    @GetMapping("/get-totalbookingprice-bybooking")
    public ResponseEntity<?> gettotalpookingvalueofuser(Booking booking){
        int result=bookingService.getTotalBookingPriceByUser(booking);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    
    @GetMapping("/get-bookings-by-a-person/{aadharCard}")
    public ResponseEntity<?> getBookings(@PathVariable("aadharCard")Integer aadharCard)
    {
        int result=userService.userbookings(aadharCard);
        return new ResponseEntity<>(result,HttpStatus.OK);
        //return 0;
    }

    @PutMapping("/update-facilities")
    public ResponseEntity<?> updateFacilities(@RequestBody List<Facility> newFacilities,@RequestParam String hotelName){
        Hotel hotel =hotelService.updatehotelfecilities(newFacilities,hotelName);
        return new ResponseEntity<>(ResponseEntity.ok(hotel),HttpStatus.OK);
        //return null;
    }

    @GetMapping("/get-hotel-byname")
    public Object gethotelbyname(@RequestParam String name){
        return hotelService.getHotelByName(name);
    }

}
