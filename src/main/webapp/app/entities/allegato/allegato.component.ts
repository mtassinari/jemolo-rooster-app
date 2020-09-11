import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAllegato } from 'app/shared/model/allegato.model';
import { AllegatoService } from './allegato.service';
import { AllegatoDeleteDialogComponent } from './allegato-delete-dialog.component';

@Component({
  selector: 'jhi-allegato',
  templateUrl: './allegato.component.html',
})
export class AllegatoComponent implements OnInit, OnDestroy {
  allegatoes?: IAllegato[];
  eventSubscriber?: Subscription;

  constructor(
    protected allegatoService: AllegatoService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.allegatoService.query().subscribe((res: HttpResponse<IAllegato[]>) => (this.allegatoes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAllegatoes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAllegato): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInAllegatoes(): void {
    this.eventSubscriber = this.eventManager.subscribe('allegatoListModification', () => this.loadAll());
  }

  delete(allegato: IAllegato): void {
    const modalRef = this.modalService.open(AllegatoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.allegato = allegato;
  }
}
