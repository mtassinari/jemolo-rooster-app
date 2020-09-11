import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IAllegato } from 'app/shared/model/allegato.model';

@Component({
  selector: 'jhi-allegato-detail',
  templateUrl: './allegato-detail.component.html',
})
export class AllegatoDetailComponent implements OnInit {
  allegato: IAllegato | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ allegato }) => (this.allegato = allegato));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
