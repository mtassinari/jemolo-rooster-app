import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAmbitoCompetenza } from 'app/shared/model/ambito-competenza.model';
import { AmbitoCompetenzaService } from './ambito-competenza.service';
import { AmbitoCompetenzaDeleteDialogComponent } from './ambito-competenza-delete-dialog.component';

@Component({
  selector: 'jhi-ambito-competenza',
  templateUrl: './ambito-competenza.component.html',
})
export class AmbitoCompetenzaComponent implements OnInit, OnDestroy {
  ambitoCompetenzas?: IAmbitoCompetenza[];
  eventSubscriber?: Subscription;

  constructor(
    protected ambitoCompetenzaService: AmbitoCompetenzaService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ambitoCompetenzaService.query().subscribe((res: HttpResponse<IAmbitoCompetenza[]>) => (this.ambitoCompetenzas = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAmbitoCompetenzas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAmbitoCompetenza): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAmbitoCompetenzas(): void {
    this.eventSubscriber = this.eventManager.subscribe('ambitoCompetenzaListModification', () => this.loadAll());
  }

  delete(ambitoCompetenza: IAmbitoCompetenza): void {
    const modalRef = this.modalService.open(AmbitoCompetenzaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ambitoCompetenza = ambitoCompetenza;
  }
}
