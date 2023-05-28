import { Injectable } from '@angular/core';
import { Puzzle } from 'src/model/puzzle';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PuzzleService {
  
  constructor(private http: HttpClient){
    
  }

  public create(puzzle: Puzzle){
    return this.http.post<Puzzle>(
      `${environment.urlPuzzle}`, puzzle).pipe(
          catchError(this.handleError)
      );
  }

  public delete(id:number){
    return this.http.delete(
      `${environment.urlPuzzle}${id}`).pipe(
          catchError(this.handleError)
      );
  }

  public find(id:number){
    return this.http.get<Puzzle>(
      `${environment.urlPuzzle}${id}`).pipe(
          catchError(this.handleError)
    );
  }

  public findAll(page:number, size:number){
    return this.http.get<Puzzle[]>(
      `${environment.urlPuzzle}${page}/${size}`).pipe(
          catchError(this.handleError)
      );
  }

  public update(puzzle:Puzzle){
    return this.http.put<Puzzle>(
      `${environment.urlPuzzle}${puzzle.id}`, puzzle).pipe(
          catchError(this.handleError)
      );
  }

  private handleError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
        const err = error || '';
        errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
        errMsg = error.message ? error.message : error.toString();
    }

    return throwError(errMsg);
  }
}
