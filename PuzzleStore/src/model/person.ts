import { Role } from "./role";

export abstract class Person {
    constructor(
        public id: number,
        public username: string,
        public password: string,
        public rol: Role
    ) {}
}