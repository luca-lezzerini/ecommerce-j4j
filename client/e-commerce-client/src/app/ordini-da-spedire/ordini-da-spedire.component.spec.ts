import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdiniDaSpedireComponent } from './ordini-da-spedire.component';

describe('OrdiniDaSpedireComponent', () => {
  let component: OrdiniDaSpedireComponent;
  let fixture: ComponentFixture<OrdiniDaSpedireComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrdiniDaSpedireComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdiniDaSpedireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
