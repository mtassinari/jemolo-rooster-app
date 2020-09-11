import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStatoRegistrazione } from 'app/shared/model/stato-registrazione.model';

@Component({
  selector: 'jhi-stato-registrazione-detail',
  templateUrl: './stato-registrazione-detail.component.html',
})
export class StatoRegistrazioneDetailComponent implements OnInit {
  statoRegistrazione: IStatoRegistrazione | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ statoRegistrazione }) => (this.statoRegistrazione = statoRegistrazione));
  }

  previousState(): void {
    window.history.back();
  }
}
