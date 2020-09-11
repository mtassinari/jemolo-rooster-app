import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProvincia } from 'app/shared/model/provincia.model';
import { ProvinciaService } from './provincia.service';
import { ProvinciaDeleteDialogComponent } from './provincia-delete-dialog.component';

@Component({
  selector: 'jhi-provincia',
  templateUrl: './provincia.component.html',
})
export class ProvinciaComponent implements OnInit, OnDestroy {
  provincias?: IProvincia[];
  eventSubscriber?: Subscription;

  constructor(protected provinciaService: ProvinciaService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.provinciaService.query().subscribe((res: HttpResponse<IProvincia[]>) => (this.provincias = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProvincias();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProvincia): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProvincias(): void {
    this.eventSubscriber = this.eventManager.subscribe('provinciaListModification', () => this.loadAll());
  }

  delete(provincia: IProvincia): void {
    const modalRef = this.modalService.open(ProvinciaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.provincia = provincia;
  }
}
