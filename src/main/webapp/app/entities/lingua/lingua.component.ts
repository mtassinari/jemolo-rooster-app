import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILingua } from 'app/shared/model/lingua.model';
import { LinguaService } from './lingua.service';
import { LinguaDeleteDialogComponent } from './lingua-delete-dialog.component';

@Component({
  selector: 'jhi-lingua',
  templateUrl: './lingua.component.html',
})
export class LinguaComponent implements OnInit, OnDestroy {
  linguas?: ILingua[];
  eventSubscriber?: Subscription;

  constructor(protected linguaService: LinguaService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.linguaService.query().subscribe((res: HttpResponse<ILingua[]>) => (this.linguas = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLinguas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILingua): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLinguas(): void {
    this.eventSubscriber = this.eventManager.subscribe('linguaListModification', () => this.loadAll());
  }

  delete(lingua: ILingua): void {
    const modalRef = this.modalService.open(LinguaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.lingua = lingua;
  }
}
