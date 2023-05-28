import { Puzzle } from "./puzzle";
export class PuzzleOrder {
    constructor(
        public id: number,
        public count: number,
        public puzzle: Puzzle
    ) {
    }
}