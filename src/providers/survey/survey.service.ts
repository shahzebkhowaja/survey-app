import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Question } from "./question";

import { Observable, of, throwError } from "rxjs";
import { catchError, map, tap } from 'rxjs/operators';
import { Answer } from "./answer";
import { resetComponentState } from "@angular/core/src/render3/instructions";
import { Stats } from "./stats";



@Injectable()
export class SurveyService {
    baseUrl: string = 'http://localhost:8080/survey/';
    questionsApi: string = 'questions';
    answersApi: string = 'answers';
    statsApi: string = 'stats';

    constructor(private httpClient: HttpClient) {}

    getQuestions(): Observable<Question[]> {
        return this.httpClient.get<Question[]>(this.baseUrl + this.questionsApi)
            .pipe(
                catchError(this.handleError('getQuestions', []))
            );
    }

    submitAnswers(answers: Answer[]): Observable<boolean> {
        return this.httpClient.post<any>((this.baseUrl + this.answersApi), answers)
            .pipe(
                map((response: Response) => {
                    return response;
                }),
                catchError(err => [])
            )
    }

    getSurveyStats(): Observable<Stats[]> {
        return this.httpClient.get<Stats[]>(this.baseUrl + this.statsApi)
            .pipe(
                catchError(this.handleError('getSurveyStats', []))
            )
    }

    private handleError<T> (operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
    
          // TODO: send the error to remote logging infrastructure
          console.error(error); // log to console instead
    
          // TODO: better job of transforming error for user consumption
          console.log(`${operation} failed: ${error.message}`);
    
          // Let the app keep running by returning an empty result.
          return of(result as T);
        };
      }

}