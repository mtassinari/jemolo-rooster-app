import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAmbitoCompetenza } from 'app/shared/model/ambito-competenza.model';

@Component({
  selector: 'jhi-ambito-competenza-detail',
  templateUrl: './ambito-competenza-detail.component.html'
})
export class AmbitoCompetenzaDetailComponent implements OnInit {
  ambitoCompetenza: IAmbitoCompetenza | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ambitoCompetenza }) => (this.ambitoCompetenza = ambitoCompetenza));
  }

  previousState(): void {
    window.history.back();
  }
}
