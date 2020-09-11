import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITitoloStudio } from 'app/shared/model/titolo-studio.model';
import { TitoloStudioService } from './titolo-studio.service';
import { TitoloStudioDeleteDialogComponent } from './titolo-studio-delete-dialog.component';

@Component({
  selector: 'jhi-titolo-studio',
  templateUrl: './titolo-studio.component.html',
})
export class TitoloStudioComponent implements OnInit, OnDestroy {
  titoloStudios?: ITitoloStudio[];
  eventSubscriber?: Subscription;

  constructor(
    protected titoloStudioService: TitoloStudioService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.titoloStudioService.query().subscribe((res: HttpResponse<ITitoloStudio[]>) => (this.titoloStudios = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTitoloStudios();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITitoloStudio): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTitoloStudios(): void {
    this.eventSubscriber = this.eventManager.subscribe('titoloStudioListModification', () => this.loadAll());
  }

  delete(titoloStudio: ITitoloStudio): void {
    const modalRef = this.modalService.open(TitoloStudioDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.titoloStudio = titoloStudio;
  }
}
