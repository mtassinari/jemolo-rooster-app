import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JemoloRoosterAppSharedModule } from 'app/shared/shared.module';
import { ProvinciaComponent } from './provincia.component';
import { ProvinciaDetailComponent } from './provincia-detail.component';
import { ProvinciaUpdateComponent } from './provincia-update.component';
import { ProvinciaDeleteDialogComponent } from './provincia-delete-dialog.component';
import { provinciaRoute } from './provincia.route';

@NgModule({
  imports: [JemoloRoosterAppSharedModule, RouterModule.forChild(provinciaRoute)],
  declarations: [ProvinciaComponent, ProvinciaDetailComponent, ProvinciaUpdateComponent, ProvinciaDeleteDialogComponent],
  entryComponents: [ProvinciaDeleteDialogComponent],
})
export class JemoloRoosterAppProvinciaModule {}
