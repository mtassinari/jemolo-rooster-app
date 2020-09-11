import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JemoloRoosterAppSharedModule } from 'app/shared/shared.module';
import { AllegatoComponent } from './allegato.component';
import { AllegatoDetailComponent } from './allegato-detail.component';
import { AllegatoUpdateComponent } from './allegato-update.component';
import { AllegatoDeleteDialogComponent } from './allegato-delete-dialog.component';
import { allegatoRoute } from './allegato.route';

@NgModule({
  imports: [JemoloRoosterAppSharedModule, RouterModule.forChild(allegatoRoute)],
  declarations: [AllegatoComponent, AllegatoDetailComponent, AllegatoUpdateComponent, AllegatoDeleteDialogComponent],
  entryComponents: [AllegatoDeleteDialogComponent],
})
export class JemoloRoosterAppAllegatoModule {}
