import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAnagraficaCandidato } from 'app/shared/model/anagrafica-candidato.model';

@Component({
  selector: 'jhi-anagrafica-candidato-detail',
  templateUrl: './anagrafica-candidato-detail.component.html'
})
export class AnagraficaCandidatoDetailComponent implements OnInit {
  anagraficaCandidato: IAnagraficaCandidato | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ anagraficaCandidato }) => (this.anagraficaCandidato = anagraficaCandidato));
  }

  previousState(): void {
    window.history.back();
  }
}
