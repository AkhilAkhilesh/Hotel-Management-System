package com.driver.Service;

import com.driver.model.Booking;
import com.driver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
public class UserService {
    @Autowired
            BookingService bookingService;
    HashMap<Integer, User> userdb;

    public UserService(){
        this.userdb=new HashMap<Integer, User>();
    }

    public String addUserintodb(User user){
        if(user!=null){
            if(userdb.containsKey(user.getaadharCardNo())){
                return "Hotel Already Exists in DB";
            }
            userdb.put(user.getaadharCardNo(),user);
        }
        return "User Successfully Added";
    }

    public User findbyadhar(int aadhar){
        return userdb.getOrDefault(aadhar,null);
    }

    public int userbookings(int aadhar){
        int count=0;
        for(Booking booking:bookingService.bookingdb.values()){
            if(booking.getBookingAadharCard()==aadhar) count++;
        }
        return count;
    }
}
