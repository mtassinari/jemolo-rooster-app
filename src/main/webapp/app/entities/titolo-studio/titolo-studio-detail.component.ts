import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITitoloStudio } from 'app/shared/model/titolo-studio.model';

@Component({
  selector: 'jhi-titolo-studio-detail',
  templateUrl: './titolo-studio-detail.component.html',
})
export class TitoloStudioDetailComponent implements OnInit {
  titoloStudio: ITitoloStudio | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ titoloStudio }) => (this.titoloStudio = titoloStudio));
  }

  previousState(): void {
    window.history.back();
  }
}
