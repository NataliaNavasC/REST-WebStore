import { PuzzleOrder } from "./puzzleOrder";
export class Purchase {

    public puzzleOrders:PuzzleOrder[];
    public shippingTax:number;
    public shippingCharge:number;
    public productsTotal:number;
    public productsCount:number;

    constructor(
        public id: number,
        public date: Date,
        public deliveryAddress: string,
        public paymentMethod: string,
        public total: number,
        public username: string
    ) {
        this.shippingTax = 0.1;
        this.puzzleOrders = [];
        this.shippingCharge = 0;
        this.productsTotal = 0;
        this.productsCount = 0;
    }
}