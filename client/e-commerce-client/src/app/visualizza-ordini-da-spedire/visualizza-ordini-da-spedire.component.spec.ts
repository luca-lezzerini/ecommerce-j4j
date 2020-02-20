import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzaOrdiniDaSpedireComponent } from './visualizza-ordini-da-spedire.component';

describe('VisualizzaOrdiniDaSpedireComponent', () => {
  let component: VisualizzaOrdiniDaSpedireComponent;
  let fixture: ComponentFixture<VisualizzaOrdiniDaSpedireComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisualizzaOrdiniDaSpedireComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisualizzaOrdiniDaSpedireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
