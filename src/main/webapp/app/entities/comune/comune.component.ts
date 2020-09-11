import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IComune } from 'app/shared/model/comune.model';
import { ComuneService } from './comune.service';
import { ComuneDeleteDialogComponent } from './comune-delete-dialog.component';

@Component({
  selector: 'jhi-comune',
  templateUrl: './comune.component.html',
})
export class ComuneComponent implements OnInit, OnDestroy {
  comunes?: IComune[];
  eventSubscriber?: Subscription;

  constructor(protected comuneService: ComuneService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.comuneService.query().subscribe((res: HttpResponse<IComune[]>) => (this.comunes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInComunes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IComune): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInComunes(): void {
    this.eventSubscriber = this.eventManager.subscribe('comuneListModification', () => this.loadAll());
  }

  delete(comune: IComune): void {
    const modalRef = this.modalService.open(ComuneDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.comune = comune;
  }
}
