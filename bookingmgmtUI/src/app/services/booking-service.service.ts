import { Injectable } from '@angular/core';
import { Show } from '../model/show';
import { Movie } from '../model/movie';
import { Screen } from '../model/screen';
import { Theater } from '../model/theater';
import { BookingResponse } from '../dto/bookingresponse';
import { Ticket } from '../model/ticket';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Booking } from '../model/booking';
import { BookingDetailsResponse } from '../dto/bookingdetailsresponse';

@Injectable({
  providedIn: 'root'
})
export class BookingServiceService {
  client:HttpClient;
  baseBookingUrl="http://localhost:8086/bookings";
  
  showList:Array<Show>=[];
  movieList:Array<Movie>=[];
  theaterList:Array<Theater>=[];

  constructor(client:HttpClient) { 
    this.client=client;
    let show1=new Show(87,new Date(),new Date(),[42,58,67],"Morning","F&F");
    let show2=new Show(88,new Date(),new Date(),[4,5,6],"Day","Dhamaal");
    let show3=new Show(89,new Date(),new Date(),[22,28,47],"Night","Golmaal");
    let show4=new Show(90,new Date(),new Date(),[52,58,57],"Evening","Dhoom");
    this.showList.push(show1);
    this.showList.push(show2);
    this.showList.push(show3);
    this.showList.push(show4);

    let movie1 = new Movie(456,"F&F","abc",127,new Date("2020-05-01"),["Hindi","English"],"Action");
    let movie2 = new Movie(766,"Dhamaal","dbc",126,new Date("2020-05-26"),["Hindi","English"],"Comedy");
    let movie3 = new Movie(656,"Golmaal","abc",128,new Date("2020-05-30"),["Hindi","English"],"Comedy");
    let movie4 = new Movie(246,"Dhoom","abc",125,new Date("2020-06-15"),["Hindi","English"],"Action");
    this.movieList.push(movie1);
    this.movieList.push(movie2);
    this.movieList.push(movie3);
    this.movieList.push(movie4);

    let screen1 = new Screen(25,200,"HD",[87],1,6);
    let screen2 = new Screen(26,201,"3D",[87],1,6);
    let screen3 = new Screen(27,202,"IMax",[87],1,6);
    let screen4 = new Screen(28,203,"SD",[87],1,6);

    let theater1= new Theater(200,"pvr","Delhi",[456],[screen1],"dasd","sdsrew");
    let theater2= new Theater(201,"INox","Mumbai",[456],[screen2],"dasd","sdsrew");
    let theater3= new Theater(202,"Delight","Banglore",[456],[screen3],"dasd","sdsrew");
    let theater4= new Theater(203,"pvr gold","Chennai",[456],[screen4],"dasd","sdsrew");
    this.theaterList.push(theater1);
    this.theaterList.push(theater2);
    this.theaterList.push(theater3);
    this.theaterList.push(theater4);

     }
     
     getTheaterList(){
       return this.theaterList;
     }

     getMovieList(){
       return this.movieList;
     }
     getShowList(){
       return this.showList;
     }

     fetchAllBookings():Observable<BookingResponse[]>{
        let url=this.baseBookingUrl;
        let observable:Observable<BookingResponse[]> = this.client.get<BookingResponse[]>(url);
        return observable;
     }

     addBooking(booking:Booking):Observable<BookingDetailsResponse>{
        let url = this.baseBookingUrl+"/add";
        let result:Observable<BookingDetailsResponse> = this.client.post<BookingDetailsResponse>(url,booking);
        return result;
     }

     getBooking(bookingId:number):Observable<BookingResponse>{
        let url = this.baseBookingUrl+"/get/"+bookingId;
        let result:Observable<BookingResponse> = this.client.get<BookingResponse>(url);
        return result;
     }
     getTicket(bookingId:number):Observable<Ticket>{
      let url = this.baseBookingUrl+"/getTicket/"+bookingId;
      let result:Observable<Ticket> = this.client.get<Ticket>(url);
      return result;
   }

   cancelBooking(bookingId:number):Observable<String>{
    let url = this.baseBookingUrl+"/cancel/"+bookingId;
    let result:Observable<String> = this.client.put<String>(url,null);
    return result;
 }
}
