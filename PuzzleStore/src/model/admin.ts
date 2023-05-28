import { Person } from "./person";
import { role } from "./role";

export class Admin extends Person{
    constructor(
        id: number,
        name: string,
        password: string,
        rol: role
    ){super(id,name,password,rol)}
}