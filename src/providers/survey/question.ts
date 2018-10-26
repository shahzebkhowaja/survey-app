import { Option } from "./option";

export class Question {
    questionId: number;
    questionText: string;
    questionType: string;
    answerText: string;
    options: Option[];
}
