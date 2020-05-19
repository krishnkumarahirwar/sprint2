import { Theater } from './theater';

export class BookingModel{
    city:string;
    selectedSeats:boolean[];
    constructor(city:string,selectedSeats:boolean[]){
        this.city=city;
        this.selectedSeats=selectedSeats;
    }
}