import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvaliarFerragemComponent } from './avaliar-ferragem.component';

describe('AvaliarFerragemComponent', () => {
  let component: AvaliarFerragemComponent;
  let fixture: ComponentFixture<AvaliarFerragemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvaliarFerragemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AvaliarFerragemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
