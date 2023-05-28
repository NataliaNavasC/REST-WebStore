import { PuzzleOrder } from "./puzzleOrder";
import { User } from "./user";

export class Cart {
    
    public puzzleOrders:PuzzleOrder[]; 

    constructor(
        public id:number,
        public user: User
    ) {
        this.puzzleOrders = [];
    }
}