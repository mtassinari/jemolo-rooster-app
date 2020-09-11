import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICompetenza } from 'app/shared/model/competenza.model';

@Component({
  selector: 'jhi-competenza-detail',
  templateUrl: './competenza-detail.component.html',
})
export class CompetenzaDetailComponent implements OnInit {
  competenza: ICompetenza | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ competenza }) => (this.competenza = competenza));
  }

  previousState(): void {
    window.history.back();
  }
}
