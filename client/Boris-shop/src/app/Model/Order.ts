import { User } from './User';
import { Product} from './Product';


export class Order {
    orderID: number;
    buyer: User;
    items: Product[];
    status: Status;

}

export enum Status {
    REQUESTED = 1,
    PAYED = 2,
    IN_PREPARATION = 3,
    SENT = 4,
    RECEIVED = 5
}