import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IComune } from 'app/shared/model/comune.model';

@Component({
  selector: 'jhi-comune-detail',
  templateUrl: './comune-detail.component.html',
})
export class ComuneDetailComponent implements OnInit {
  comune: IComune | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ comune }) => (this.comune = comune));
  }

  previousState(): void {
    window.history.back();
  }
}
