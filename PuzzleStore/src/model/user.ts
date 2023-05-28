import { Person } from "./person";
import { Purchase } from "./purchase";
import { Role } from "./role";

export class User extends Person{
    public purchases:Purchase[];

    constructor(
        id: number,
        name: string,
        password: string,
        rol: Role
    ){
        super(id,name,password,rol);
        this.purchases = [];
    }
}