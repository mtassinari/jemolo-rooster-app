import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IStatoRegistrazione } from 'app/shared/model/stato-registrazione.model';
import { StatoRegistrazioneService } from './stato-registrazione.service';
import { StatoRegistrazioneDeleteDialogComponent } from './stato-registrazione-delete-dialog.component';

@Component({
  selector: 'jhi-stato-registrazione',
  templateUrl: './stato-registrazione.component.html',
})
export class StatoRegistrazioneComponent implements OnInit, OnDestroy {
  statoRegistraziones?: IStatoRegistrazione[];
  eventSubscriber?: Subscription;

  constructor(
    protected statoRegistrazioneService: StatoRegistrazioneService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.statoRegistrazioneService
      .query()
      .subscribe((res: HttpResponse<IStatoRegistrazione[]>) => (this.statoRegistraziones = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInStatoRegistraziones();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IStatoRegistrazione): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInStatoRegistraziones(): void {
    this.eventSubscriber = this.eventManager.subscribe('statoRegistrazioneListModification', () => this.loadAll());
  }

  delete(statoRegistrazione: IStatoRegistrazione): void {
    const modalRef = this.modalService.open(StatoRegistrazioneDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.statoRegistrazione = statoRegistrazione;
  }
}
