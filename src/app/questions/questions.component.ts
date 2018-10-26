import { OnInit, Component } from "@angular/core";
import { SurveyService } from "src/providers/survey/survey.service";
import { Question } from "src/providers/survey/question";
import { Answer } from "src/providers/survey/answer";
import { Router } from "@angular/router";

@Component({
    templateUrl: './questions.component.html',
    styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {

    questions: Question[] = [];

    constructor(
        private surveyService: SurveyService,
        private router: Router) {}

    ngOnInit() {
        this.surveyService.getQuestions().subscribe(
            response => {
                this.questions = response;
            },
            error => {
                console.log("Error");
            }
        );
    }

    setRadioValue(questionId: number, optionId: number) {
        this.questions.find(question => question.questionId == questionId).options
            .forEach(option => {
                option.selected = false;
                if (option.optionId == optionId) {
                    option.selected = true;
                }
            });
    }

    submitSurvey() {
        var answers: Answer[] = [];

        this.questions.forEach(question => {
            var optionIds: number[] = [];
            question.options.forEach(option => {
                if (option.selected) {
                    optionIds.push(option.optionId);
                }
            })

            var answer = new Answer();
            answer.questionId = question.questionId;
            answer.optionIds = optionIds;
            answer.answerText = question.answerText;

            answers.push(answer);
        });

        this.surveyService.submitAnswers(answers).subscribe(
            response => {
                if (response) {
                    this.router.navigate(['/stats']);
                }
                else {
                    
                }
            },
            error => {
                console.log("Error");
            }
        );
    }

}