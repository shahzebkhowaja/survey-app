import { OnInit, Component } from "@angular/core";
import { SurveyService } from "src/providers/survey/survey.service";
import { Stats } from "src/providers/survey/stats";
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { single } from './data';

@Component({
    templateUrl: './stats.component.html',
    styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {

    statsList: Stats[] = [];

    single: any[];

    view: any[] = [800, 200];

    // options
    showXAxis = true;
    showYAxis = true;
    gradient = false;
    showLegend = true;
    showXAxisLabel = true;
    xAxisLabel = 'Option Count';
    showYAxisLabel = true;
    yAxisLabel = 'Options';
    legendTitle = "Options"

    constructor(private surveyService: SurveyService) {}

    ngOnInit() {
        this.surveyService.getSurveyStats().subscribe(
            response => {
                this.statsList = response;
            },
            error => {
                console.log("Error");
            }
        );
    }

}