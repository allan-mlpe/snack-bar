import {Snack} from './Snack';

export interface Order {
    id?: string;
    snack?: Snack;
    price?: number;
    discount?: number; 
}